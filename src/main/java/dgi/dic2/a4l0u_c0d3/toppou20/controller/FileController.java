package dgi.dic2.a4l0u_c0d3.toppou20.controller;

import dgi.dic2.a4l0u_c0d3.toppou20.model.File;
import dgi.dic2.a4l0u_c0d3.toppou20.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<File> uploadFile(@RequestParam("file") MultipartFile file,
                                           @RequestParam("entityId") Long entityId,
                                           @RequestParam("entityType") String entityType) throws IOException {
        File savedFile = fileService.saveFile(file, entityId, entityType);
        return ResponseEntity.ok(savedFile);
    }

    @GetMapping("/entity/{entityId}/{entityType}")
    public ResponseEntity<List<File>> getFilesByEntity(@PathVariable Long entityId, @PathVariable String entityType) {
        List<File> files = fileService.getFilesByEntity(entityId, entityType);
        return ResponseEntity.ok(files);
    }
}
