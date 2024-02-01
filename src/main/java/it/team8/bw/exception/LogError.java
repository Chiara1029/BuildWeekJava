package it.team8.bw.exception;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Setter
@NoArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "error_log")
public class LogError {
    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ToString.Include
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

//    @Enumerated(EnumType.STRING)
//    private Level level; // INFO, DEBUG, ERROR, ecc.

    @ToString.Include
    private String message;

    public LogError(Date timestamp, String message) {
        this.timestamp = timestamp;
//        this.level = level;
        this.message = message;
    }

}