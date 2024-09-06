package com.example.demo.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Model.FileMetadata;
import com.example.demo.Repo.RepoInterface;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FileService {

    private final RepoInterface fileRepository;

    public FileService(RepoInterface fileRepository) {
        this.fileRepository = fileRepository;
    }

    public FileMetadata saveFile(MultipartFile file) throws Exception {
        FileMetadata fileEntity = new FileMetadata();
        fileEntity.setName(file.getOriginalFilename());
        fileEntity.setType(file.getContentType());
        fileEntity.setSize(file.getSize());
        fileEntity.setCreatedAt(LocalDateTime.now());
        fileEntity.setData(file.getBytes());

        return fileRepository.save(fileEntity);
    }

    public Optional<FileMetadata> getFile(Long fileId) {
        return fileRepository.findById(fileId);
    }

    public List<FileMetadata> listFiles() {
        return fileRepository.findAll();
    }

    public FileMetadata updateFile(Long fileId, MultipartFile newFile) throws Exception {
        Optional<FileMetadata> optionalFile = fileRepository.findById(fileId);

        if (optionalFile.isPresent()) {
            FileMetadata fileEntity = optionalFile.get();
            fileEntity.setData(newFile.getBytes());
            fileEntity.setSize(newFile.getSize());
            fileEntity.setType(newFile.getContentType());
            return fileRepository.save(fileEntity);
        }

        throw new Exception("File not found");
    }

    public void deleteFile(Long fileId) {
        fileRepository.deleteById(fileId);
    }
}
