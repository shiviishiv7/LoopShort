package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasMany;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Post type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Posts", authRules = {
  @AuthRule(allow = AuthStrategy.OWNER, ownerField = "owner", identityClaim = "cognito:username", operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ }),
  @AuthRule(allow = AuthStrategy.PRIVATE, operations = { ModelOperation.READ }),
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.READ })
})
@Index(name = "todosByTitle", fields = {"title"})
@Index(name = "todosByOwner", fields = {"owner"})
public final class Post implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField TITLE = field("title");
  public static final QueryField OWNER = field("owner");
  public static final QueryField VIEW = field("view");
  public static final QueryField DESCRIPTION = field("description");
  public static final QueryField IMAGE_URL = field("imageUrl");
  public static final QueryField VIDEO_URL = field("videoUrl");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String title;
  private final @ModelField(targetType="String") String owner;
  private final @ModelField(targetType="String") String view;
  private final @ModelField(targetType="String") String description;
  private final @ModelField(targetType="String") String imageUrl;
  private final @ModelField(targetType="String") String videoUrl;
  private final @ModelField(targetType="Comment") @HasMany(associatedWith = "postID", type = Comment.class) List<Comment> comments = null;
  public String getId() {
      return id;
  }
  
  public String getTitle() {
      return title;
  }
  
  public String getOwner() {
      return owner;
  }
  
  public String getView() {
      return view;
  }
  
  public String getDescription() {
      return description;
  }
  
  public String getImageUrl() {
      return imageUrl;
  }
  
  public String getVideoUrl() {
      return videoUrl;
  }
  
  public List<Comment> getComments() {
      return comments;
  }
  
  private Post(String id, String title, String owner, String view, String description, String imageUrl, String videoUrl) {
    this.id = id;
    this.title = title;
    this.owner = owner;
    this.view = view;
    this.description = description;
    this.imageUrl = imageUrl;
    this.videoUrl = videoUrl;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Post post = (Post) obj;
      return ObjectsCompat.equals(getId(), post.getId()) &&
              ObjectsCompat.equals(getTitle(), post.getTitle()) &&
              ObjectsCompat.equals(getOwner(), post.getOwner()) &&
              ObjectsCompat.equals(getView(), post.getView()) &&
              ObjectsCompat.equals(getDescription(), post.getDescription()) &&
              ObjectsCompat.equals(getImageUrl(), post.getImageUrl()) &&
              ObjectsCompat.equals(getVideoUrl(), post.getVideoUrl());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getTitle())
      .append(getOwner())
      .append(getView())
      .append(getDescription())
      .append(getImageUrl())
      .append(getVideoUrl())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Post {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("title=" + String.valueOf(getTitle()) + ", ")
      .append("owner=" + String.valueOf(getOwner()) + ", ")
      .append("view=" + String.valueOf(getView()) + ", ")
      .append("description=" + String.valueOf(getDescription()) + ", ")
      .append("imageUrl=" + String.valueOf(getImageUrl()) + ", ")
      .append("videoUrl=" + String.valueOf(getVideoUrl()))
      .append("}")
      .toString();
  }
  
  public static TitleStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static Post justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Post(
      id,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      title,
      owner,
      view,
      description,
      imageUrl,
      videoUrl);
  }
  public interface TitleStep {
    BuildStep title(String title);
  }
  

  public interface BuildStep {
    Post build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep owner(String owner);
    BuildStep view(String view);
    BuildStep description(String description);
    BuildStep imageUrl(String imageUrl);
    BuildStep videoUrl(String videoUrl);
  }
  

  public static class Builder implements TitleStep, BuildStep {
    private String id;
    private String title;
    private String owner;
    private String view;
    private String description;
    private String imageUrl;
    private String videoUrl;
    @Override
     public Post build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Post(
          id,
          title,
          owner,
          view,
          description,
          imageUrl,
          videoUrl);
    }
    
    @Override
     public BuildStep title(String title) {
        Objects.requireNonNull(title);
        this.title = title;
        return this;
    }
    
    @Override
     public BuildStep owner(String owner) {
        this.owner = owner;
        return this;
    }
    
    @Override
     public BuildStep view(String view) {
        this.view = view;
        return this;
    }
    
    @Override
     public BuildStep description(String description) {
        this.description = description;
        return this;
    }
    
    @Override
     public BuildStep imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
    
    @Override
     public BuildStep videoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }
    
    /** 
     * WARNING: Do not set ID when creating a new object. Leave this blank and one will be auto generated for you.
     * This should only be set when referring to an already existing object.
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     * @throws IllegalArgumentException Checks that ID is in the proper format
     */
    public BuildStep id(String id) throws IllegalArgumentException {
        this.id = id;
        
        try {
            UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
        } catch (Exception exception) {
          throw new IllegalArgumentException("Model IDs must be unique in the format of UUID.",
                    exception);
        }
        
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String title, String owner, String view, String description, String imageUrl, String videoUrl) {
      super.id(id);
      super.title(title)
        .owner(owner)
        .view(view)
        .description(description)
        .imageUrl(imageUrl)
        .videoUrl(videoUrl);
    }
    
    @Override
     public CopyOfBuilder title(String title) {
      return (CopyOfBuilder) super.title(title);
    }
    
    @Override
     public CopyOfBuilder owner(String owner) {
      return (CopyOfBuilder) super.owner(owner);
    }
    
    @Override
     public CopyOfBuilder view(String view) {
      return (CopyOfBuilder) super.view(view);
    }
    
    @Override
     public CopyOfBuilder description(String description) {
      return (CopyOfBuilder) super.description(description);
    }
    
    @Override
     public CopyOfBuilder imageUrl(String imageUrl) {
      return (CopyOfBuilder) super.imageUrl(imageUrl);
    }
    
    @Override
     public CopyOfBuilder videoUrl(String videoUrl) {
      return (CopyOfBuilder) super.videoUrl(videoUrl);
    }
  }
  
}
