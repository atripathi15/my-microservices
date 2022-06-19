package com.ecomm.user.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ecomm.user.exception.UserServiceException;
import com.ecomm.user.model.UserVO;
import com.ecomm.user.service.UserService;
import com.ecomm.user.utils.CommonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.JsonPatchOperation;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
    protected ObjectMapper objectMapper;    

	
	@PostMapping
	public ResponseEntity<Object> addUser(@Valid @RequestBody UserVO userVO) {
		int userId = userService.addUser(userVO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userId).toUri();
        return ResponseEntity.created(uri).build();
		
	}
	
	@GetMapping
	public ResponseEntity<List<UserVO>> getAllUsers() {
		List<UserVO> userList = userService.getAllUsers();		
		return new ResponseEntity<List<UserVO>>(userList, HttpStatus.OK);
		
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserVO> getUser(@PathVariable int userId) {
		UserVO user = userService.getUser(userId);	
		return new ResponseEntity<UserVO>(user, HttpStatus.OK);
		
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<Object> updateUser(@Valid @RequestBody UserVO userVO, @PathVariable int userId){
		int uid = userService.updateUser(userId, userVO);
		return new ResponseEntity<>("User updated", HttpStatus.OK);
	}
	
	@PatchMapping("/{userId}")
	public ResponseEntity<Object> partialUpdateUser(@PathVariable int userId, @RequestBody List<JsonPatchOperation> patchOperations) throws UserServiceException {
		// unfortunately, JsonPatchOperation does not expose 'path' for external access.
        List<String> pathList = CommonUtils.extractPathsFromPatchOperations(patchOperations, objectMapper);

        // you could load these unmodifiable field set for each resource.
        // Currently, this is hard coded.
        List<String> notAllowedFields = Arrays.asList("/id");
        if (CommonUtils.isAnyMatch(pathList, notAllowedFields)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some of provided field are not allowed to patch!");
        }

        UserVO user = this.userService.getUser(userId);
        this.userService.updateUser(userId, patchEntity(user, UserVO.class, patchOperations));
		return new ResponseEntity<>("User updated", HttpStatus.OK);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
	}
	
	<T> T patchEntity(T entity, Class<T> clz, List<JsonPatchOperation> patchOperations) {
        JsonNode userJsonNode = objectMapper.convertValue(entity, JsonNode.class);
        try {
            JsonPatch patchRef = new JsonPatch(patchOperations);
            JsonNode patchedNode = patchRef.apply(userJsonNode);
            return objectMapper.treeToValue(patchedNode, clz);

        } catch (JsonPatchException | JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
	
	

}
