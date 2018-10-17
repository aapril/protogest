package com.pfe.ldb.member.security;


import com.pfe.ldb.entities.UserEntity;


public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(UserEntity user) {
        return new JwtUser(
                user.getId().longValue(),
                user.getUsername(),
                user.getMember().getFirstName(),
                user.getMember().getLastName(),
                user.getMember().getEmail(),
                user.getPassword()
        );
    }
/*
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toList());
    }
    */
}
