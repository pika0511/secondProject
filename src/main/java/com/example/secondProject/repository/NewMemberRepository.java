package com.example.secondProject.repository;

import com.example.secondProject.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface NewMemberRepository extends CrudRepository<Member, Long> {
}
