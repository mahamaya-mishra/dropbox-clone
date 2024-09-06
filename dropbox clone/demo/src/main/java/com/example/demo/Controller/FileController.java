package com.example.demo.Controller;

import com.example.demo.Model.FileMetadata;
import com.example.demo.Service.FileService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            FileMetadata savedFile = fileService.saveFile(file);
            return new ResponseEntity<>("File uploaded successfully with ID: " + savedFile.getId(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to upload file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long fileId) {
        Optional<FileMetadata> fileEntity = fileService.getFile(fileId);
        return fileEntity.map(file -> ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(file.getData()))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{fileId}")
    public ResponseEntity<String> updateFile(@PathVariable Long fileId, @RequestParam("file") MultipartFile newFile) {
        try {
            fileService.updateFile(fileId, newFile);
            return new ResponseEntity<>("File updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{fileId}")
    public ResponseEntity<String> deleteFile(@PathVariable Long fileId) {
        fileService.deleteFile(fileId);
        return new ResponseEntity<>("File deleted successfully", HttpStatus.OK);
    }

    @GetMapping
    public List<FileMetadata> listFiles() {
        return fileService.listFiles();
    }
}
