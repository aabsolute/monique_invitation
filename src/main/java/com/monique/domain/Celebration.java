package com.monique.domain;

import com.monique.gallery.dto.GalleryDTO;
import com.monique.main.dto.CelebrationDTO;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.List;

@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(builderMethodName = "CelebrationBuilder")
@Entity
public class Celebration extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="celebration_id", length=1, updatable = false)
    private long id;

    @Column(length = 2048, nullable = false)
    private String comment;

    @Column(length = 64, nullable = false)
    private String name;

    @Column(length = 32, nullable = false)
    private String passkey;

    @Convert(converter=BooleanToStringConverter.class)
    @Column(name="delete_yn", length=1)
    private String delete_yn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id")
    private User user;

    public static Celebration.CelebrationBuilder builder(CelebrationDTO cbt) {
        return CelebrationBuilder()
                .id(cbt.getId())
                .comment(cbt.getComment())
                .name(cbt.getName())
                .passkey(cbt.getPasskey());
    }
    @PrePersist
    public void prePersist(){
        this.delete_yn="N";
    }

}


@Converter
class BooleanToStringConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean value) {
        return (value != null && value) ? "Y" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String value) {
        return "Y".equals(value);
    }

}
