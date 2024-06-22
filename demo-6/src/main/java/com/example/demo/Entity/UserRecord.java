package com.example.demo.Entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.util.Date;
import java.util.Arrays;

@Entity
@Table(name = "user_record", uniqueConstraints = {
	    @UniqueConstraint(columnNames = "email"),
	    @UniqueConstraint(columnNames = "mob_no")
	})
public class UserRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Nonnull
    private String fname;

    @Nonnull
    private String lname;

    private String gender;

    private Date dob;

    @Nonnull
    private String email;

    private String hobbies;

    private String address;

    private Long mob_no;

    private String job;

    @Lob
    @Column(name = "profile_image", columnDefinition = "LONGBLOB")
    private byte[] profileImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getMob_no() {
        return mob_no;
    }

    public void setMob_no(Long mob_no) {
        this.mob_no = mob_no;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }

	@Override
	public String toString() {
		return "UserRecord [id=" + id + ", fname=" + fname + ", lname=" + lname + ", gender=" + gender + ", dob=" + dob
				+ ", email=" + email + ", hobbies=" + hobbies + ", address=" + address + ", mob_no=" + mob_no + ", job="
				+ job + ", profileImage=" + Arrays.toString(profileImage) + "]";
	}

   
    
    }

