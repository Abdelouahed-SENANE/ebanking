package ma.youcode.ebanking.mappers;

import ma.senane.utilities.mappers.GenericMapper;
import org.mapstruct.Mapper;
import ma.youcode.ebanking.dtos.request.RoleRequestDTO;
import ma.youcode.ebanking.dtos.response.RoleResponseDTO;
import ma.youcode.ebanking.entities.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper extends GenericMapper<Role , RoleResponseDTO , RoleRequestDTO> {}
