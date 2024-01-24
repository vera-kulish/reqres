package tests;

import adapters.LoginAdapter;
import adapters.RegisterAdapter;
import adapters.ResourceAdapter;
import adapters.UsersAdapter;
import com.github.javafaker.Faker;
import models.*;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ReqresTest {

    @Test
    public void listUsers() {
        UserList userList = new UsersAdapter().getList();
        System.out.println(userList.getData());
    }

    @Test
    public void singleUser() {
        UserResponse user = new UsersAdapter().getUser();
        System.out.println(user.getData());
    }

    @Test
    public void singleUserNotFound() {
        UserResponse response = new UsersAdapter().getUserNotFound();
        assertEquals(response.getData(), null);
    }

    @Test
    public void listResource() {
        ResourceList resources = new ResourceAdapter().listResource();
        System.out.println(resources.getData());
    }

    @Test
    public void singleResource() {
        ResourceResponse response = new ResourceAdapter().getResource();
        System.out.println(response.getData());
    }

    @Test
    public void singleResourceNotFound() {
        ResourceResponse response = new ResourceAdapter().getResourceNotFound();
        assertEquals(response.getData(), null);
    }

    @Test
    public void createUser() {
        Faker faker = new Faker();
        String name = faker.name().fullName();
        String job = faker.job().position();
        CreatedUser user = new UsersAdapter().create(name, job);
        System.out.println(user);
        assertEquals(user.getName(), name);
        assertEquals(user.getJob(), job);
    }

    @Test
    public void updateUser() {
        Faker faker = new Faker();
        String name = faker.name().fullName();
        String job = faker.job().position();
        UpdatedUser user = new UsersAdapter().updatePut(name, job);
        System.out.println(user);
        assertEquals(user.getName(), name);
        assertEquals(user.getJob(), job);

        name = faker.name().fullName();
        job = faker.job().position();
        user = new UsersAdapter().updatePatch(name, job);
        System.out.println(user);
        assertEquals(user.getName(), name);
        assertEquals(user.getJob(), job);
    }

    @Test
    public void deleteUser() {
        new UsersAdapter().delete();
    }

    @Test
    public void register() {
        RegisterResponse response = new RegisterAdapter().register();
        System.out.println(response);
    }

    @Test
    public void registerUnsuccessful() {
        String error = new RegisterAdapter().registerUnsuccessful();
        assertEquals(error, "{\"error\":\"Missing password\"}");
    }

    @Test
    public void login() {
        LoginResponse response = new LoginAdapter().login();
        System.out.println(response);
    }

    @Test
    public void loginUnsuccessful() {
        String error = new LoginAdapter().loginUnsuccessful();
        assertEquals(error, "{\"error\":\"Missing password\"}");
    }

    @Test
    public void delayedResponse() {
        UserList userList = new UsersAdapter().getListWithDelay();
        System.out.println(userList.getData());
    }
}
