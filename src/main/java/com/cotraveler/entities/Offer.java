/*
 * Copyright (c)  Aleksey Plekhanov 03.02.2023, 03:14
 */

package com.cotraveler.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fromLabelName;
    private String toLabelName;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date finishTime;
    private String clientId;
    private String comment;
    private Boolean autoClosed;
}
