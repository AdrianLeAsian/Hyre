package CC17.khryzalle.hyre;

public class NewsFeedItem {
    private String authorName;
    private String postType;
    private String title;
    private String content;
    private String imageUri;
    private String fileUri;

    public NewsFeedItem(String authorName, String postType, String title, String content, 
                       String imageUri, String fileUri) {
        this.authorName = authorName;
        this.postType = postType;
        this.title = title;
        this.content = content;
        this.imageUri = imageUri;
        this.fileUri = fileUri;
    }

    public String getAuthorName() { return authorName; }
    public String getPostType() { return postType; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getImageUri() { return imageUri; }
    public String getFileUri() { return fileUri; }
} 