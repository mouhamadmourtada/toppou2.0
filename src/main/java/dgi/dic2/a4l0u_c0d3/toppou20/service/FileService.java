package dgi.dic2.a4l0u_c0d3.toppou20.service;

import dgi.dic2.a4l0u_c0d3.toppou20.repository.FileRepository;
import dgi.dic2.a4l0u_c0d3.toppou20.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Service
public class FileService {
    private FileRepository fileRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File saveFile(MultipartFile file, Long entityId, String entityType) throws IOException {

        // Vérifier si le répertoire existe, sinon le créer
        Path directory = Paths.get(uploadDir);
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }


        // Chemin complet du fichier à enregistrer
        Path filePath = Paths.get(uploadDir, file.getOriginalFilename());

        // Copier les données du fichier dans le système de fichiers
        Files.write(filePath, file.getBytes());


        // Créer et enregistrer l'entité File dans la base de données avec les métadonnées
        File newFile = new File();
        newFile.setFileName(file.getOriginalFilename());
        newFile.setFileType(file.getContentType());
        newFile.setUploadDate(new Date());
        newFile.setEntityId(entityId);
        newFile.setEntityType(entityType);
        // Stocker le chemin du fichier dans le système de fichiers au lieu des données
        newFile.setFilePath(filePath.toString());
        return fileRepository.save(newFile);
    }

    public List<File> getFilesByEntity(Long entityId, String entityType) {
        return fileRepository.findByEntityIdAndEntityType(entityId, entityType);
    }
}
