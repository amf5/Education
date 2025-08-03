package com.com.Courses.serviceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.com.Courses.service.CloudnairyServiceI;
@Service
public class CloudnairyServiceImpl implements CloudnairyServiceI {

	private final Cloudinary cloudinary;

    
    public  CloudnairyServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }
    
    // upload image to cloudinary
	@Override
	public String uploadFileFromStream(InputStream inputStream) throws IOException {
        try {
            Map<String, Object> uploadResult = cloudinary.uploader().uploadLarge(
                    inputStream,
                    ObjectUtils.asMap(
                            "resource_type", "image",
                            "chunk_size", 6_000_000, 
                            "folder", "images",       
                            "use_filename", true,
                            "unique_filename", true
                    )
            );
            return uploadResult.get("secure_url").toString(); 
        } catch (Exception e) {
            throw new RuntimeException("Cloudinary image upload failed: " + e.getMessage(), e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println("Error closing InputStream: " + e.getMessage());
                }
            }
        }
    }


}
