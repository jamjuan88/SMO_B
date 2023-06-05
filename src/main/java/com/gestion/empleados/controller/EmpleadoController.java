
package com.gestion.empleados.controller;

import com.gestion.empleados.models.Empleado;
import com.gestion.empleados.models.Falta;
import com.gestion.empleados.services.EmpleadoService;
import com.gestion.empleados.services.FaltaService;
import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpleadoController {  

    
    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private FaltaService faltaService;
     
    //Metodo para mostrar todos los empleados
    @GetMapping("/empleados")
    public ResponseEntity<List<Empleado>> listarTodosLosEmpleados(){
        return new ResponseEntity<>(empleadoService.findAll(), HttpStatus.OK);
    }    
    
    
    //metodo para crear Empleados
   @PostMapping("/empleados")
   public ResponseEntity<Empleado> guardarEmpleado(@RequestBody Empleado empleado) {
       return new ResponseEntity<>(empleadoService.save(empleado),HttpStatus.CREATED);
    } 
   
     //metodo para buscar un empleado por id  
    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorID(@PathVariable Long id){
        Empleado empleado = empleadoService.findById(id);                            
        return ResponseEntity.ok(empleado);
    }
    
    //metodo para actualizar datos del empleado
    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long id,@RequestBody Empleado empleado){
        Empleado empleadoEncontrado = empleadoService.findById(id);
                            
        if(empleadoEncontrado == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            empleadoEncontrado.setNombre(empleado.getNombre());
            empleadoEncontrado.setApellido(empleado.getApellido());
            empleadoEncontrado.setDni(empleado.getDni());
            empleadoEncontrado.setCuil(empleado.getCuil());
            empleadoEncontrado.setEmail(empleado.getEmail());
            empleadoEncontrado.setTelefono(empleado.getTelefono());
            empleadoEncontrado.setFechaNac(empleado.getFechaNac());
            empleadoEncontrado.setFechaIngreso(empleado.getFechaIngreso());
            empleadoEncontrado.setDireccion(empleado.getDireccion());
            empleadoEncontrado.setGenero(empleado.getGenero());
            empleadoEncontrado.setAntecedentes(empleado.getAntecedentes());
            empleadoEncontrado.setCategoria(empleado.getCategoria());
            empleadoEncontrado.setFotoPerfil(empleado.getFotoPerfil());
            empleadoEncontrado.setEmpresa(empleado.getEmpresa());
            empleadoEncontrado.setPuesto(empleado.getPuesto());
            empleadoEncontrado.setEstadoLaboral(empleado.getEstadoLaboral());
            return new ResponseEntity<>(empleadoService.save(empleadoEncontrado),HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
              
        //este metodo sirve para eliminar un empleado
	@DeleteMapping("/empleados/{id}")
	public ResponseEntity<?> eliminarEmpleado(@PathVariable Long id){
		empleadoService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
    }
   


        
         //METODOS PARA LOS ARCHIVOS
    @PostMapping("/empleados/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
             @RequestParam("employeeId") String employeeId,
             @RequestParam("fileName") String fileName){
        
        File targetFile = new File("C:/Users/Usuario/Desktop/SistemaSMO/com.smo.spring/uploads/" + employeeId + "/otrosDoc/" + fileName);
        targetFile.getParentFile().mkdirs();
        try {
            file.transferTo(targetFile);
                 
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Archivo subido correctamente");
    }
    
    

//meotodo eliminar archivos
    @DeleteMapping("/empleados/files/{employeeId}/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String employeeId, @PathVariable String fileName) {
    try {
      File targetFile = new File("C:/Users/Usuario/Desktop/SistemaSMO/com.smo.spring/uploads/" + employeeId + "/otrosDoc/" + fileName);
      targetFile.delete();
      return ResponseEntity.ok("Archivo eliminado correctamente");
    } catch (Exception e) {
          return ResponseEntity.badRequest().body("Error al eliminar el archivo");
    }
  }


      @GetMapping("/empleados/files/{employeeId}")
         public List<String> getEmployeeFiles(@PathVariable String employeeId) {
         File employeeDir = new File("C:/Users/Usuario/Desktop/SistemaSMO/com.smo.spring/uploads/" + employeeId + "/otrosDoc/");
         String[] files = employeeDir.list();
         return Arrays.asList(files);
}


    @GetMapping("/empleados/files/{employeeId}/{fileName}")
      public ResponseEntity<Resource> getFile(@PathVariable String employeeId, @PathVariable String fileName) {
      File file = new File("C:/Users/Usuario/Desktop/SistemaSMO/com.smo.spring/uploads/" + employeeId + "/otrosDoc/" + fileName);
      HttpHeaders headers = new HttpHeaders();
      headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + fileName);
       if (fileName.endsWith(".pdf")) {
        headers.setContentType(MediaType.APPLICATION_PDF);
       } else if (fileName.endsWith(".jpg")) {
        headers.setContentType(MediaType.IMAGE_JPEG);
       }
      Resource resource = new FileSystemResource(file);
      return ResponseEntity.ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource);
     }        
      
@GetMapping("/empleados/archivosFalta/{employeeId}/{idFalta}/{nombreArch}")
public ResponseEntity<Resource> getFile2(@PathVariable String employeeId, @PathVariable Number idFalta ,@PathVariable String nombreArch) {
  File file = new File("C:/Users/Usuario/Desktop/SistemaSMO/com.smo.spring/uploads/" + employeeId + "/" + idFalta + "/" + nombreArch);
  HttpHeaders headers = new HttpHeaders();
  headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + nombreArch);
  if (nombreArch.endsWith(".pdf")) {
    headers.setContentType(MediaType.APPLICATION_PDF);
  } else if (nombreArch.endsWith(".jpg")) {
    headers.setContentType(MediaType.IMAGE_JPEG);
  }
  Resource resource = new FileSystemResource(file);
  return ResponseEntity.ok()
        .headers(headers)
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .body(resource);
}

       
}
      
      


    