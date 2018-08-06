package com.example.bartek.systemgromadzacyzdjecia;

public class Upload {
    private String mName;
    private String mEmail;
    private String mImageUrl;
    private String mReview;

    public Upload() {
        //empty constructor needed
    }

    public Upload(String name, String imageUrl, String email) {
        if (name.trim().equals("")) {
            name = "No Name";
        }

        mName = name;
        mImageUrl = imageUrl;
        mEmail = email;
        mReview = "No review Yet";
    }

    public String getReview() {return mReview; }

    public void setReview(String review) { mReview = review; }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setEmail(String email) { mEmail = email; }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getEmail() { return mEmail; }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }
}
