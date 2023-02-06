/*
 * Copyright (c)  Aleksey Plekhanov 03.02.2023, 03:14
 */

package com.cotraveler.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;

//@Configuration
public class DatabaseInitializer {
//    @Autowired
    JdbcTemplate jdbcTemplate;

//    @Bean
    CommandLineRunner loadDatabase() {
        return new CommandLineRunner() {

            @Override
            public void run(String... args) {

                jdbcTemplate.execute("create table OFFER (ID long primary key "
                        + "auto_increment, OFFERNAME varchar(30))");

                jdbcTemplate.execute("insert into OFFER (OFFERNAME) "
                        + "values ('Will Smith')");
            }
        };
    }
}
