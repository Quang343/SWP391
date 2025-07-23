package com.example.AgriculturalWarehouseManagement.Backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "blogdetail")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class BlogDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blogdetailid")
    private Integer blogDetailID;

    // Khóa ngoại tham chiếu tới Blog
    @OneToOne
    @JoinColumn(name = "blogid", referencedColumnName = "blogid")
    @JsonBackReference
    private Blog blog;

    @Column(name = "thumbnail")
    private String thumbnail;

    @NotBlank(message = "Nội dung chi tiết không được để trống")
    @Column(name = "detailcontent")
    private String detailContent;


}
