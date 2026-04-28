package anki.hw.controller;

import anki.hw.service.DeckService;
import anki.hw.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class DeckController {

    private final DeckService deckService;
    private final MemberService memberService;

    @GetMapping("/decks")
    public String list(Model model) {
        model.addAttribute("decks", deckService.findDecksWithMember());
        return "decks/deckList";
    }

    @GetMapping("/decks/new")
    public String createForm(Model model) {
        model.addAttribute("members", memberService.findMembers());
        return "decks/createDeckForm";
    }

    @PostMapping("/decks/new")
    public String create(@RequestParam Long memberId,
                         @RequestParam String name) {
        deckService.createDeck(memberId, name);
        return "redirect:/decks";
    }
}
