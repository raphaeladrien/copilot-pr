package com.example.copilot_pr.controller;

import com.example.copilot_pr.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private List<User> users = new ArrayList<>();

    @PostMapping("/process")
    public String processUser(@RequestBody User user,
                              @RequestParam(required = false) String mode,
                              @RequestParam(required = false) String region) {

        if (user == null) {
            return "User is null";
        }

        if (user.name == null || user.name.isEmpty()) {
            return "Invalid name";
        }

        if (user.age < 0) {
            return "Invalid age";
        }

        if (user.salary < 0) {
            return "Invalid salary";
        }

        if (mode != null) {

            if (mode.equals("PROMOTE")) {

                if (user.department != null) {

                    if (user.department.equals("IT")) {
                        if (user.age > 30) {
                            user.salary += user.salary * 0.2;
                        } else {
                            user.salary += user.salary * 0.1;
                        }
                    } else if (user.department.equals("HR")) {
                        if (user.age > 40) {
                            user.salary += user.salary * 0.15;
                        } else {
                            user.salary += user.salary * 0.05;
                        }
                    } else {
                        user.salary += 1000;
                    }

                } else {
                    return "Department required for promotion";
                }

            } else if (mode.equals("BONUS")) {

                if (region != null) {

                    if (region.equals("US")) {
                        if (user.salary > 80000) {
                            user.salary += 5000;
                        } else if (user.salary > 50000) {
                            user.salary += 3000;
                        } else {
                            user.salary += 1000;
                        }
                    } else if (region.equals("EU")) {
                        if (user.active) {
                            user.salary += 2000;
                        } else {
                            user.salary += 500;
                        }
                    } else {
                        user.salary += 300;
                    }

                } else {
                    return "Region required for bonus";
                }

            } else if (mode.equals("TERMINATE")) {

                if (user.active) {
                    if (user.age > 60) {
                        user.active = false;
                        return "Retired";
                    } else if (user.salary > 100000) {
                        user.active = false;
                        return "Terminated with compensation";
                    } else {
                        user.active = false;
                        return "Terminated";
                    }
                } else {
                    return "User already inactive";
                }

            } else {
                return "Unknown mode";
            }

        } else {

            if (user.salary > 100000 && user.age > 50 && user.active) {
                user.salary += 10000;
            } else if (user.salary > 70000 && user.age > 40) {
                user.salary += 5000;
            } else if (user.salary > 50000 || user.age > 35) {
                user.salary += 2000;
            } else {
                user.salary += 500;
            }
        }

        users.add(user);

        if (users.size() > 100) {
            if (users.size() > 500) {
                return "System overloaded";
            } else {
                return "Approaching capacity";
            }
        }

        return "Processed successfully";
    }
}
