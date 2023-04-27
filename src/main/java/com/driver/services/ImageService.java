package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog = blogRepository2.findById(blogId).get();
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);
        blog.getImageList().add(image);
        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {

        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        String[] screen_arr = screenDimensions.split("X");
        int screen_length = Integer.parseInt(screen_arr[0]);
        int screen_breadth = Integer.parseInt(screen_arr[1]);

        Image image = imageRepository2.findById(id).get();

        String[] image_arr = image.getDimensions().split("X");
        int image_length = Integer.parseInt(image_arr[0]);
        int image_breadth = Integer.parseInt(image_arr[1]);

        return (screen_length/image_length)*(screen_breadth/image_breadth);
    }
}