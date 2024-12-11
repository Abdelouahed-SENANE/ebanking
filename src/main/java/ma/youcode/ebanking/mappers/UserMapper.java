package ma.youcode.ebanking.mappers;

import ma.senane.utilities.mappers.GenericMapper;
import ma.youcode.ebanking.entities.Role;
import org.mapstruct.Mapper;
import ma.youcode.ebanking.dtos.request.UserRequestDTO;
import ma.youcode.ebanking.dtos.response.UserResponseDTO;
import ma.youcode.ebanking.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<User, UserResponseDTO, UserRequestDTO> {

}
