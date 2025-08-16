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
        }}
        

        @Override
        public void deleteImageByUrl(String imageUrl) throws IOException {
            try {
              
                String publicId = extractPublicIdFromUrl(imageUrl);

               
                Map<String, Object> deleteResult = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
                System.out.println("Delete result: " + deleteResult);

            } catch (Exception e) {
                throw new RuntimeException("Cloudinary image delete failed: " + e.getMessage(), e);
            }
        }

 
        private String extractPublicIdFromUrl(String imageUrl) {
        
            String withoutUploadPart = imageUrl.substring(imageUrl.indexOf("/upload/") + 8);

      
            if (withoutUploadPart.startsWith("v")) {
                withoutUploadPart = withoutUploadPart.substring(withoutUploadPart.indexOf("/") + 1);
            }

   
            int dotIndex = withoutUploadPart.lastIndexOf(".");
            if (dotIndex != -1) {
                withoutUploadPart = withoutUploadPart.substring(0, dotIndex);
            }

            return withoutUploadPart;
        }

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
   

	

}
