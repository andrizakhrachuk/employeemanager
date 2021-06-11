package com.employee.manager.web;

import com.employee.manager.dto.GroupDto;
import com.employee.manager.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/groups")
    public List<GroupDto> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping("/groups/{groupId}")
    public ResponseEntity<GroupDto> getGroup(@PathVariable Long groupId) {
        return groupService.getGroup(groupId)
                .map(groupDto -> ResponseEntity.ok().body(groupDto))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/groups")
    public ResponseEntity<GroupDto> createGroup(@RequestBody @Valid GroupDto group) {
        return ResponseEntity.ok().body(groupService.createGroup(group));
    }

    @PutMapping("/groups/{groupId}")
    public ResponseEntity<GroupDto> updateGroup(@RequestBody @Valid GroupDto groupDto) {
        return ResponseEntity.ok().body(groupService.updateGroup(groupDto));
    }

    @DeleteMapping("/groups/{groupId}")
    public ResponseEntity<GroupDto> deleteGroup(@PathVariable Long groupId) {
        groupService.deleteGroup(groupId);

        return ResponseEntity.ok().build();
    }
}
