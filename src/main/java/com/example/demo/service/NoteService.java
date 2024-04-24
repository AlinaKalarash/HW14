package com.example.demo.service;

import com.example.demo.entity.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

// List<Note> listAll() - повертає список всіх нотаток
// Note add(Note note) - додає нову нотатку, генеруючи для цієї нотатки унікальний (випадковий) числовий ідентифікатор,
//  повертає цю ж нотатку з згенерованим ідентифікатором.
// void deleteById(long id) - видаляє нотатку з вказаним ідентифікатором. Якщо нотатки з ідентифікатором немає -
//  викидає виключення.
// void update(Note note) - шукає нотатку по note.id. Якщо нотатка є - оновлює для неї title та content.
//  Якщо нотатки немає - викидає виключення.
// Note getById(long id) - повертає нотатку по її ідентифікатору. Якщо нотатки немає - викидає виключення.
// Використай анотацію @Service, щоб зробити цей клас Spring Bean.

@Service
public class NoteService {
    List<Note> notes = new ArrayList<>();

    public List<Note> listAll() {
        return notes.stream().collect(Collectors.toList());
    }

    public Note add(Note note) {
        long i = new Random().nextInt();
        note.setId(i);
        notes.add(note);
        return note;
    }

    public void deleteById(long id) {
        Note note = getById(id);
        notes.remove(note);
    }

    public void update(Note note) {
        long id = note.getId();
        Note updateNote = getById(id);
        updateNote.setTitle(note.getTitle());
        updateNote.setContext(note.getContext());
    }

    public Note getById(long id) {
        return notes.stream()
                .filter(note -> note.getId() == id).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Note with id "+ id + " is not found"));
    }

}
