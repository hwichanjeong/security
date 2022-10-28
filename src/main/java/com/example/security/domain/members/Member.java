package com.example.security.domain.members;

import com.example.security.web.dto.MemberFormDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Table(name = "memberr")
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String info;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Builder
    public Member(String name, String email, String password, String info, MemberRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.info = info;
        this.role = role;
    }

    public void modify(String info,String name){
        this.info = info;
        this.name = name;
    }
    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        Member member = Member.builder()
                .name(memberFormDto.getName())
                .email(memberFormDto.getEmail())
                .info(memberFormDto.getInfo())
                .password(passwordEncoder.encode(memberFormDto.getPassword()))  //암호화처리
                .role(MemberRole.USER)
                .build();
        return member;
    }
}
