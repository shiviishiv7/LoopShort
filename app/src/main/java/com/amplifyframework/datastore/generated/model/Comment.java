package com.amplifyframework.datastore.generated.model;


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

/** This is an auto generated class representing the Comment type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Comments", authRules = {
  @AuthRule(allow = AuthStrategy.OWNER, ownerField = "owner", identityClaim = "cognito:username", operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ }),
  @AuthRule(allow = AuthStrategy.PRIVATE, operations = { ModelOperation.READ }),
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.READ })
})
@Index(name = "byPost", fields = {"postID","content"})
public final class Comment implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField POST_ID = field("postID");
  public static final QueryField CONTENT = field("content");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="ID", isRequired = true) String postID;
  private final @ModelField(targetType="String", isRequired = true) String content;
  public String getId() {
      return id;
  }
  
  public String getPostId() {
      return postID;
  }
  
  public String getContent() {
      return content;
  }
  
  private Comment(String id, String postID, String content) {
    this.id = id;
    this.postID = postID;
    this.content = content;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Comment comment = (Comment) obj;
      return ObjectsCompat.equals(getId(), comment.getId()) &&
              ObjectsCompat.equals(getPostId(), comment.getPostId()) &&
              ObjectsCompat.equals(getContent(), comment.getContent());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getPostId())
      .append(getContent())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Comment {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("postID=" + String.valueOf(getPostId()) + ", ")
      .append("content=" + String.valueOf(getContent()))
      .append("}")
      .toString();
  }
  
  public static PostIdStep builder() {
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
  public static Comment justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Comment(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      postID,
      content);
  }
  public interface PostIdStep {
    ContentStep postId(String postId);
  }
  

  public interface ContentStep {
    BuildStep content(String content);
  }
  

  public interface BuildStep {
    Comment build();
    BuildStep id(String id) throws IllegalArgumentException;
  }
  

  public static class Builder implements PostIdStep, ContentStep, BuildStep {
    private String id;
    private String postID;
    private String content;
    @Override
     public Comment build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Comment(
          id,
          postID,
          content);
    }
    
    @Override
     public ContentStep postId(String postId) {
        Objects.requireNonNull(postId);
        this.postID = postId;
        return this;
    }
    
    @Override
     public BuildStep content(String content) {
        Objects.requireNonNull(content);
        this.content = content;
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
    private CopyOfBuilder(String id, String postId, String content) {
      super.id(id);
      super.postId(postId)
        .content(content);
    }
    
    @Override
     public CopyOfBuilder postId(String postId) {
      return (CopyOfBuilder) super.postId(postId);
    }
    
    @Override
     public CopyOfBuilder content(String content) {
      return (CopyOfBuilder) super.content(content);
    }
  }
  
}
