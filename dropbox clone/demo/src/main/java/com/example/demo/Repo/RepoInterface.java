package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.FileMetadata;

public interface RepoInterface extends JpaRepository<FileMetadata, Long> {
}
