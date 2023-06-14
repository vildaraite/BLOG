package lt.code.academy.blog.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.code.academy.blog.validator.annotation.FieldsComparator;
import lt.code.academy.blog.validator.annotation.Password;
import lt.code.academy.blog.validator.annotation.PhoneNumber;
import lt.code.academy.blog.entity.UserEntity;
import lt.code.academy.blog.validator.data.PhoneType;

import java.util.UUID;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldsComparator(first = "password", second = "repeatPassword")
public class User {
    private UUID id;
    @NotBlank
    @Size(min = 5, max = 50)
    private String name;
    /*@NotBlank
    @Size(min = 2, max = 100)*/
    private String surname;
    @Email
    private String email;
    @NotBlank
    @PhoneNumber
    private String phone;
    @NotBlank
    @Size(min = 10)
    private String password;
    @NotBlank
    private String repeatPassword;

    public static User convert(UserEntity entity) {
        return new User(entity.getId(),
                entity.getName(),
                entity.getSurname(),
                entity.getEmail(),
                entity.getPhone(),
                null,
                null);
    }
}