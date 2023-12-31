package org.cuong.paginationandsortdemo.controller;

import org.cuong.paginationandsortdemo.entity.Actor;
import org.cuong.paginationandsortdemo.repository.ActorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ActorController {
    private final ActorRepository actorRepository;

    public ActorController(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @GetMapping("/")
    String getActors(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(defaultValue = "") String query,
            Model model) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);

        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("pageSizeList", List.of(5, 9, 10, 15));

//        Page<Actor> result = actorRepository.searchActorByName(query, pageable);
        Page<Actor> result = actorRepository
                .findAllByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(query, query, pageable);

        model.addAttribute("actors", result);

        return "home";
    }
}
