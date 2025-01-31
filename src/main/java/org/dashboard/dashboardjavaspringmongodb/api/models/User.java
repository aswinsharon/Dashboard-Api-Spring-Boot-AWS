package org.dashboard.dashboardjavaspringmongodb.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "users")
public class User {
    @Id
    private final String _id;
    private final String username;
    private final String email;
    private final String password;
    private final String img;
    private boolean isActive;
    private boolean isAdmin;
    private final String phone;
    private final String address;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private final Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private final Date updatedAt;

    public User(String _id, String username, String email, String password, String img, Object isActive, Object isAdmin,
            String phone, String address, Date createdAt, Date updatedAt) {
        this._id = _id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.img = img;
        this.phone = phone;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.initializeFlags(isActive, isAdmin);
    }

    private void initializeFlags(Object isActive, Object isAdmin) {
        if (isActive instanceof String) {
            this.isActive = "yes".equalsIgnoreCase((String) isActive);
        } else if (isActive instanceof Boolean) {
            this.isActive = (Boolean) isActive;
        }

        if (isAdmin instanceof String) {
            this.isAdmin = "yes".equalsIgnoreCase((String) isAdmin);
        } else if (isAdmin instanceof Boolean) {
            this.isAdmin = (Boolean) isAdmin;
        }
    }

    public String getId() {
        return _id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getImg() {
        return img;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
