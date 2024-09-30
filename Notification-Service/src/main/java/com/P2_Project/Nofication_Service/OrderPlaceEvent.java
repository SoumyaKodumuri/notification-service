package com.P2_Project.Nofication_Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderPlaceEvent {

    private String orderId;
    private String userEmail;
}
