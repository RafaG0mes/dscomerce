package br.com.rgsystems.dscomerce.projections;

public interface UserDetailProjection {
    String getUsername();
    String getPassword();
    Long getRoleId();
    String getAuthority();
}
