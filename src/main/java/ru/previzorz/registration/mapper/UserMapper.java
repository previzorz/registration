package ru.previzorz.registration.mapper;

import org.mapstruct.Mapper;
import ru.previzorz.registration.dto.UserDTO;
import ru.previzorz.registration.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);
}
