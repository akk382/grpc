package org.grpc.protobuf;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public class Employee {
    private int id;
    private String name;
    private double salary;
    private List<Department> departments;
    private Map<String, String> addressMap;
    private boolean isActive;
    private byte[] profilePicture;
    private Instant joinDate;
}
