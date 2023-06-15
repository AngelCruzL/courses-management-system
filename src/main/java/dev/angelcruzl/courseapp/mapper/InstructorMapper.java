package dev.angelcruzl.courseapp.mapper;

import dev.angelcruzl.courseapp.dto.InstructorDTO;
import dev.angelcruzl.courseapp.entity.Instructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class InstructorMapper {
  public InstructorDTO fromInstructor(Instructor instructor) {
    InstructorDTO instructorDTO = new InstructorDTO();
    BeanUtils.copyProperties(instructor, instructorDTO);
    return instructorDTO;
  }

  public Instructor fromInstructorDTO(InstructorDTO instructorDTO) {
    Instructor instructor = new Instructor();
    BeanUtils.copyProperties(instructorDTO, instructor);
    return instructor;
  }
}
