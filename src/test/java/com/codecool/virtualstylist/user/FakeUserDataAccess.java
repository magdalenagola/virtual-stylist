package com.codecool.virtualstylist.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface FakeUserDataAccess extends UserDataAccess{
    List<User> users = new ArrayList<>();


    @Override
    default <S extends User> S save(S s){
        users.add(s);
        return s;
    }

    @Override
    default Page<User> findAllByRolesIs(Role role, Pageable paging){
        return new PageImpl<>(users,paging,users.size());
    }

    @Override
    default Integer countAllByRolesIs(Role role){
        return users.size();
    }

    @Override
    default void delete(User userToDelete){
         users.stream()
                .filter(user -> user.getId().equals(userToDelete.getId()))
                .findAny()
                .ifPresent(users::remove);
    }

    @Override
    default Optional<User> findUserById(int id){
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findAny();
    }
}

