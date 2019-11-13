package com.pflb.SpringTest.data.entities;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
public class TestProfile {
    public List<Request> requests;
}
