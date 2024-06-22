package com.example.demo.Controller;

import com.example.demo.Entity.UserRecord;
import com.example.demo.Repository.UserRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserRecordController {

    @Autowired
    private UserRecordRepository userRecordRepository;

    @PostMapping
    public ResponseEntity<UserRecord> createUser(
            @RequestParam("fname") String fname,
            @RequestParam("lname") String lname,
            @RequestParam("gender") String gender,
            @RequestParam("dob") String dobStr,
            @RequestParam("email") String email,
            @RequestParam("hobbies") String hobbies,
            @RequestParam("address") String address,
            @RequestParam("mob_no") Long mobNo,
            @RequestParam("job") String job,
            @RequestParam(value = "profileImage", required = false) MultipartFile profileImage) {

        try {
            UserRecord userRecord = new UserRecord();
            userRecord.setFname(fname);
            userRecord.setLname(lname);
            userRecord.setGender(gender);
            userRecord.setDob(new SimpleDateFormat("yyyy-MM-dd").parse(dobStr));
            userRecord.setEmail(email);
            userRecord.setHobbies(hobbies);
            userRecord.setAddress(address);
            userRecord.setMob_no(mobNo);
            userRecord.setJob(job);
            if (profileImage != null && !profileImage.isEmpty()) {
                userRecord.setProfileImage(profileImage.getBytes());
            }

            UserRecord savedUser = userRecordRepository.save(userRecord);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRecord> getUserById(@PathVariable int id) {
        Optional<UserRecord> userRecord = userRecordRepository.findById(id);
        return userRecord.map(record -> new ResponseEntity<>(record, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<UserRecord>> getAllUsers() {
        List<UserRecord> userRecords = userRecordRepository.findAll();
        return new ResponseEntity<>(userRecords, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable int id) {
        try {
            userRecordRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRecord> updateUser(
            @PathVariable int id,
            @RequestParam("fname") String fname,
            @RequestParam("lname") String lname,
            @RequestParam("gender") String gender,
            @RequestParam("dob") String dobStr,
            @RequestParam("email") String email,
            @RequestParam("hobbies") String hobbies,
            @RequestParam("address") String address,
            @RequestParam("mob_no") Long mobNo,
            @RequestParam("job") String job,
            @RequestParam(value = "profileImage", required = false) MultipartFile profileImage) {

        try {
            Optional<UserRecord> optionalUserRecord = userRecordRepository.findById(id);
            if (!optionalUserRecord.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            UserRecord existingUser = optionalUserRecord.get();
            existingUser.setFname(fname);
            existingUser.setLname(lname);
            existingUser.setGender(gender);
            existingUser.setDob(new SimpleDateFormat("yyyy-MM-dd").parse(dobStr));
            existingUser.setEmail(email);
            existingUser.setHobbies(hobbies);
            existingUser.setAddress(address);
            existingUser.setMob_no(mobNo);
            existingUser.setJob(job);
            if (profileImage != null && !profileImage.isEmpty()) {
                existingUser.setProfileImage(profileImage.getBytes());
            }

            UserRecord updatedUser = userRecordRepository.save(existingUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
