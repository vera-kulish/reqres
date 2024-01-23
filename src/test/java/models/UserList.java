package models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.ArrayList;

@Data
public class UserList {
    int page;
    @SerializedName("per_page")
    int perPage;
    int total;
    @SerializedName("total_pages")
    int totalPages;
    ArrayList<User> data;
}
