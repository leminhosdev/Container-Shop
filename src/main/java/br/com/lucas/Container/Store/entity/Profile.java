package br.com.lucas.Container.Store.entity;

public enum Profile {
    ADMIN,
    USER;
    private String profile;
    public void Profile(String profile){
        this.profile = profile;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
