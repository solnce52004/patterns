package com.example.patterns.behavioral.iterator.v1;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.*;

public class Iterator {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Please specify social network to target spam tool (default:Facebook):");
        System.out.println("1. Facebook");
        System.out.println("2. LinkedIn");
        String choice = scanner.nextLine();

        SocialNetwork network;
        final List<Profile> profiles = createProfiles();
        network = choice.equals("2")
                ? new LinkedIn(profiles)
                : new Facebook(profiles);

        Spammer spammer = new Spammer(network);
        spammer.sendSpamToFriends(
                "anna.smith@bing.com",
                "Hey! This is Anna's friend Josh.");
        spammer.sendSpamToCoworkers(
                "anna.smith@bing.com",
                "Hey! This is Anna's boss Jason.");
    }

    public static List<Profile> createProfiles() {
        List<Profile> data = new ArrayList<>();
        data.add(new Profile(
                "anna.smith@bing.com",
                "Anna Smith",
                "friends:mad_max@ya.com", "friends:bill@microsoft.eu",
                "coworkers:mad_max@ya.com"));
        data.add(new Profile(
                "mad_max@ya.com",
                "Maximilian",
                "friends:bill@microsoft.eu",
                "coworkers:anna.smith@bing.com"));
        data.add(new Profile(
                "bill@microsoft.eu",
                "Billie",
                  "friends:mad_max@ya.com",
                "coworkers:mad_max@ya.com"));
        return data;
    }
}
@Log4j2
class Spammer {
    public SocialNetwork network;
    public ProfileIterator iterator;

    public Spammer(SocialNetwork network) {
        this.network = network;
    }

    public void sendSpamToFriends(String email, String message) {
        log.info("Iterating over friends...");
        iterator = network.createFriendsIterator(email);
        while (iterator.hasNext()) {
            Profile profile = iterator.getNext();
            sendMessage(profile.getEmail(), message);
        }
    }

    public void sendSpamToCoworkers(String profileEmail, String message) {
        log.info("Iterating over coworkers...");
        iterator = network.createCoworkersIterator(profileEmail);
        while (iterator.hasNext()) {
            Profile profile = iterator.getNext();
            sendMessage(profile.getEmail(), message);
        }
    }

    public void sendMessage(String email, String message) {
        log.info("Sent message to: {}. Message body: {}", email, message);
    }
}

@Getter
class Profile {
    private final String name;
    private final String email;
    private final Map<String, List<String>> contacts = new HashMap<>();

    public Profile(String email, String name, String... contacts) {
        this.email = email;
        this.name = name;

        for (String contact : contacts) {
            String[] parts = contact.split(":");
            String type = "friend";
            String contactEmail;

            if (parts.length == 1) {
                contactEmail = parts[0];
            } else {
                type = parts[0];
                contactEmail = parts[1];
            }

            getContacts(type).add(contactEmail);
        }
    }

    public List<String> getContacts(String contactType) {
        if (!this.contacts.containsKey(contactType)) {
            this.contacts.put(contactType, new ArrayList<>());
        }
        return contacts.get(contactType);
    }
}

@Log4j2
@Getter
class LinkedIn implements SocialNetwork {
    private final List<Profile> profiles;

    public LinkedIn(List<Profile> cache) {
        this.profiles = Objects.requireNonNullElseGet(cache, ArrayList::new);
    }

    @Override
    public Profile requestProfile(String email) {
        simulateNetworkLatency();
        log.info("LinkedIn: Loading profile {} over the network...", email);
        return findProfile(email);
    }

    @Override
    public List<String> requestProfileFriends(String email, String type) {
        simulateNetworkLatency();
        log.info("LinkedIn: Loading {} list of {} over the network...", type, email);
        Profile profile = findProfile(email);
        if (profile != null) {
            return profile.getContacts(type);
        }
        return null;
    }

    @Override
    public ProfileIterator createFriendsIterator(String email) {
        return new LinkedInIterator(this, FRIENDS, email);
    }

    @Override
    public ProfileIterator createCoworkersIterator(String email) {
        return new LinkedInIterator(this, COWORKERS, email);
    }
}

@Getter
@Log4j2
class Facebook implements SocialNetwork {
    private final List<Profile> profiles;

    public Facebook(List<Profile> cache) {
        this.profiles = Objects.requireNonNullElseGet(cache, ArrayList::new);
    }

    @Override
    public Profile requestProfile(String email) {
        simulateNetworkLatency();
        log.info("Facebook: Loading profile {} over the network...", email);
        return findProfile(email);
    }

    @Override
    public List<String> requestProfileFriends(String email, String type) {
        simulateNetworkLatency();
        log.info("Facebook: Loading {} list of {} over the network...", type, email);
        Profile profile = findProfile(email);
        if (profile != null) {
            return profile.getContacts(type);
        }
        return null;
    }

    @Override
    public ProfileIterator createFriendsIterator(String email) {
        return new FacebookIterator(this, FRIENDS, email);
    }

    @Override
    public ProfileIterator createCoworkersIterator(String email) {
        return new FacebookIterator(this, COWORKERS, email);
    }
}

interface SocialNetwork {
    String FRIENDS = "friends";
    String COWORKERS = "coworkers";
    Profile requestProfile(String email);
    List<String> requestProfileFriends(String email, String type);
    ProfileIterator createFriendsIterator(String email);
    ProfileIterator createCoworkersIterator(String email);
    List<Profile> getProfiles();

    default void simulateNetworkLatency() {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    default Profile findProfile(String email) {
        for (Profile profile : getProfiles()) {
            if (profile.getEmail().equals(email)) {
                return profile;
            }
        }
        return null;
    }
}

class LinkedInIterator implements ProfileIterator {
    private final String type;
    private final String email;
    private final LinkedIn linkedIn;
    private int currentPosition = 0;
    private final List<String> emails = new ArrayList<>();
    private final List<Profile> contacts = new ArrayList<>();

    public LinkedInIterator(LinkedIn linkedIn, String type, String email) {
        this.linkedIn = linkedIn;
        this.type = type;
        this.email = email;
    }

    private void lazyLoad() {
        if (emails.size() == 0) {
            List<String> profiles = linkedIn.requestProfileFriends(this.email, this.type);
            for (String profile : profiles) {
                this.emails.add(profile);
                this.contacts.add(null);
            }
        }
    }

    @Override
    public boolean hasNext() {
        lazyLoad();
        return currentPosition < emails.size();
    }

    @Override
    public Profile getNext() {
        if (!hasNext()) {
            return null;
        }

        String friendEmail = emails.get(currentPosition);
        Profile friendContact = contacts.get(currentPosition);
        if (friendContact == null) {
            friendContact = linkedIn.requestProfile(friendEmail);
            contacts.set(currentPosition, friendContact);
        }
        currentPosition++;
        return friendContact;
    }

    @Override
    public void reset() {
        currentPosition = 0;
    }
}

@Getter
class FacebookIterator implements ProfileIterator {
    private final String type;
    private final String email;
    private final Facebook facebook;
    private int currentPosition = 0;
    private final List<String> emails = new ArrayList<>();
    private final List<Profile> profiles = new ArrayList<>();

    public FacebookIterator(Facebook facebook, String type, String email) {
        this.facebook = facebook;
        this.type = type;
        this.email = email;
    }

    private void lazyLoad() {
        if (emails.size() == 0) {
            List<String> profileFriends = facebook.requestProfileFriends(this.email, this.type);
            for (String profile : profileFriends) {
                this.emails.add(profile);
                this.profiles.add(null);
            }
        }
    }

    @Override
    public boolean hasNext() {
        lazyLoad();
        return currentPosition < emails.size();
    }

    @Override
    public Profile getNext() {
        if (!hasNext()) {
            return null;
        }

        String friendEmail = emails.get(currentPosition);
        Profile friendProfile = profiles.get(currentPosition);
        if (friendProfile == null) {
            friendProfile = facebook.requestProfile(friendEmail);
            profiles.set(currentPosition, friendProfile);
        }
        currentPosition++;
        return friendProfile;
    }

    @Override
    public void reset() {
        currentPosition = 0;
    }
}

interface ProfileIterator {
    boolean hasNext();
    Profile getNext();
    void reset();
}
