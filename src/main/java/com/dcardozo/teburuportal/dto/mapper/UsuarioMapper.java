package com.dcardozo.teburuportal.dto.mapper;


import com.dcardozo.teburuportal.dominio.Usuario;
import com.dcardozo.teburuportal.dto.UsuarioLogInDTO;
import com.dcardozo.teburuportal.dto.UsuarioPosLoginwithIdDTO;
import com.dcardozo.teburuportal.dto.UsuarioSignUpDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario userLogindtoToEntity(UsuarioLogInDTO dto);
    UsuarioLogInDTO entityToUsuarioLogInDTO(Usuario entity);
    Usuario usuarioSignupDTOToEntity(UsuarioSignUpDTO dto);
    UsuarioPosLoginwithIdDTO entityToUsuarioPosLoginwithIdDTO(Usuario entity);

}
