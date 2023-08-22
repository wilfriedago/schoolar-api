BEGIN;
-- Inserting tuples for the 'groups' table
INSERT INTO groups (created_at, deleted_at, updated_at, id, description, name)
VALUES ('2023-08-11 08:00:00+00', NULL, '2023-08-11 08:00:00+00', 'a5c8a351-6a61-4b94-99a9-7351f4e6d8b1',
        'Welcome to the adventurous world of 1st graders! Academic Year 2023-2024', '1st Grade Class'),
       ('2023-08-11 09:30:00+00', NULL, '2023-08-11 09:30:00+00', 'e7f319d2-3d0e-42e3-99e9-4b1c25c60745',
        'Embark on a journey of learning with 2nd graders. Academic Year 2023-2024', '2nd Grade Class'),
       ('2023-08-11 10:45:00+00', NULL, NULL, 'ba8f750b-1fa6-4f50-88e1-2e5b54fabb09',
        'Exploring new horizons with the curious minds of 3rd graders. Academic Year 2023-2024', '3rd Grade Class'),
       ('2023-08-11 12:15:00+00', NULL, '2023-08-11 12:15:00+00', 'd6b3fc9e-39b1-4317-8c7c-84e6c6b1a9c7',
        'Nurturing the growth of 4th graders as they blossom. Academic Year 2023-2024', '4th Grade Class'),
       ('2023-08-11 13:45:00+00', NULL, NULL, '9e81733a-19b2-42a3-9740-7c1e70de5e2e',
        'Empowering 5th graders to reach for the stars. Academic Year 2023-2024', '5th Grade Class'),
       ('2023-08-11 14:30:00+00', NULL, '2023-08-11 14:30:00+00', 'f2481e24-df2d-4eb5-90dd-6cf7aa0fbd4a',
        'Guiding 6th graders as they become leaders of tomorrow. Academic Year 2023-2024', '6th Grade Class'),
       ('2023-08-11 15:00:00+00', NULL, NULL, '8d592e95-8d62-43b1-a6b8-5c38b3dd45c2',
        'Fostering creativity and growth in 7th graders. Academic Year 2023-2024', '7th Grade Class'),
       ('2023-08-11 16:15:00+00', NULL, '2023-08-11 16:15:00+00', '5b0f8a5c-49a0-4640-a587-bf67ef464e4c',
        'Empowering 8th graders to explore their passions. Academic Year 2023-2024', '8th Grade Class'),
       ('2023-08-11 17:30:00+00', NULL, NULL, '7a24dbcc-b2c9-4bfe-8817-32e6f056c3e6',
        'Supporting 9th graders in their journey to excellence. Academic Year 2023-2024', '9th Grade Class'),
       ('2023-08-11 18:45:00+00', NULL, '2023-08-11 18:45:00+00', '0a932234-8f94-45b4-9db7-03db4bb79ae0',
        'Elevating 10th graders towards a bright future. Academic Year 2023-2024', '10th Grade Class');

-- Inserting tuples for the 'subjects' table
INSERT INTO subjects (created_at, deleted_at, updated_at, id, description, name)
VALUES ('2023-08-11 08:00:00+00', NULL, '2023-08-11 08:00:00+00', 'f5c1ea89-2e46-4ef8-8d53-176d45c4a7b1',
        'Introduction to Mathematics: Building a strong foundation in basic mathematical concepts.', 'Mathematics'),
       ('2023-08-11 09:30:00+00', NULL, '2023-08-11 09:30:00+00', '72c8743d-ae2f-418a-8c5e-af8b875f1f62',
        'Exploring the world around us through scientific inquiry and experimentation.', 'Science'),
       ('2023-08-11 10:45:00+00', NULL, NULL, '2c6d7f85-8825-4b62-94bb-98e9700e8125',
        'Understanding the past and its impact on shaping our present and future.', 'History'),
       ('2023-08-11 12:15:00+00', NULL, '2023-08-11 12:15:00+00', 'f216b8f9-6c0f-4586-80d3-9305c06ec9a5',
        'Developing language skills through reading, writing, and communication.', 'English Language'),
       ('2023-08-11 13:45:00+00', NULL, NULL, '826caef1-cc84-4bb2-9c50-2e1da682967c',
        'Learning about various cultures, societies, and their interconnections.', 'Social Studies'),
       ('2023-08-11 14:30:00+00', NULL, '2023-08-11 14:30:00+00', 'e0b69d94-e942-418b-af44-3a2f4c01ecdb',
        'Exploring the fascinating world of living organisms and their environments.', 'Biology'),
       ('2023-08-11 15:00:00+00', NULL, NULL, '1e0a561c-5e0b-4461-94c6-19a0ce4e37c6',
        'Understanding the fundamental principles of matter, energy, and forces.', 'Physics'),
       ('2023-08-11 16:15:00+00', NULL, '2023-08-11 16:15:00+00', '942ad7d6-370d-4026-befd-c2d4c7c4b5dd',
        'Exploring the world of numbers, patterns, and relationships.', 'Algebra'),
       ('2023-08-11 17:30:00+00', NULL, NULL, '4a3ebc8d-53e6-4643-9d86-ea39f1d4cfcf',
        'Introducing the principles of computing and problem-solving through coding.', 'Computer Science'),
       ('2023-08-11 18:45:00+00', NULL, '2023-08-11 18:45:00+00', 'c849ed7f-2056-446c-b13b-88d944b8e7a4',
        'Cultivating creativity and self-expression through various artistic mediums.', 'Art'),
       ('2023-08-11 19:30:00+00', NULL, NULL, '22fe933d-9909-43c2-94a1-6cd86e1a1275',
        'Developing physical fitness, teamwork, and sportsmanship through athletic activities.', 'Physical Education'),
       ('2023-08-11 20:00:00+00', NULL, '2023-08-11 20:00:00+00', 'a17da6d1-4f60-4450-b8c3-65cc72c7c4b8',
        'Exploring different cultures, languages, and enhancing cross-cultural communication.', 'Foreign Languages'),
       ('2023-08-11 21:15:00+00', NULL, NULL, '267e1cfb-cb37-49a2-82c6-e1efacab5da6',
        'Developing critical thinking and logical reasoning skills through engaging puzzles and challenges.',
        'Logic and Problem Solving'),
       ('2023-08-11 22:30:00+00', NULL, '2023-08-11 22:30:00+00', '10b1a4ef-ec48-4d22-8d90-1f125ee9b0a9',
        'Understanding the principles of ethics, morality, and responsible decision-making.', 'Ethics'),
       ('2023-08-11 23:45:00+00', NULL, NULL, '7b7ac88d-e96a-41c2-9c99-77b2d9966d84',
        'Exploring the world of finance, budgeting, and economic systems.', 'Economics'),
       ('2023-08-12 00:30:00+00', NULL, '2023-08-12 00:30:00+00', '0bf0f1ac-6c89-4f92-b3dd-9efb7b00b248',
        'Nurturing an appreciation for literature and fostering effective communication skills.', 'Literature');

-- Inserting tuples for the 'classrooms' table
INSERT INTO classrooms (created_at, deleted_at, updated_at, id, name, description, capacity)
VALUES ('2023-08-10 08:00:00+00', NULL, '2023-08-10 08:00:00+00', 'e80b6521-25c9-4a3d-a309-56c790902f01', 'CL-501',
        'Classroom equipped with interactive whiteboard, projector, and seating for 30 students', 30),
       ('2023-08-10 09:15:00+00', NULL, '2023-08-10 09:15:00+00', '62a4d8ae-4f6d-4269-a4af-7199d648d325', 'CL-201',
        'Modern classroom with audio-visual facilities, suitable for 25 students', 25),
       ('2023-08-10 10:30:00+00', NULL, '2023-08-10 10:30:00+00', '3b7f22bf-78d0-4d99-8eae-d3182e9efdd8', 'CL-301',
        'Spacious classroom with laboratory equipment and seating for 20 students', 20),
       ('2023-08-10 11:45:00+00', NULL, '2023-08-10 11:45:00+00', 'a6c8e49d-136c-49e5-8d97-c52c29a0a92a', 'CL-102',
        'Classroom designed for literature discussions, equipped with bookshelves and comfortable seating for 25 students',
        25),
       ('2023-08-10 13:00:00+00', NULL, '2023-08-10 13:00:00+00', '85f51f0d-3d57-4aa0-bddf-92f504a9f873', 'CL-401',
        'Computer lab with individual workstations, suitable for programming and coding sessions for 30 students', 30),
       ('2023-08-10 14:15:00+00', NULL, '2023-08-10 14:15:00+00', '49c5645f-aa9d-4c27-98a7-79c70822703b', 'Lab-1',
        'Fully equipped chemistry laboratory with safety measures for 15 students', 15),
       ('2023-08-10 15:30:00+00', NULL, '2023-08-10 15:30:00+00', 'c3f7369d-45ae-4d05-9d71-9b82ec5d76bc', 'CL-202',
        'Geography classroom with maps, globes, and seating for 25 students', 25),
       ('2023-08-10 16:45:00+00', NULL, '2023-08-10 16:45:00+00', 'db2e6e88-b59f-4ab5-b123-5110ce26f546', 'Lab-2',
        'Physics laboratory with advanced equipment and safety measures for 15 students', 15),
       ('2023-08-10 18:00:00+00', NULL, '2023-08-10 18:00:00+00', 'f0c6a6f2-1d9d-4f5b-8ef5-2feeeb271c3e', 'CL-103',
        'History classroom with historical artifacts, suitable for 20 students', 20),
       ('2023-08-10 19:15:00+00', NULL, '2023-08-10 19:15:00+00', '8d52c3e1-5624-4a29-85d2-ae3ebdb31cd1', 'CL-302',
        'Chemistry classroom equipped with lab tables, fume hoods, and seating for 25 students', 25);

-- Inserting tuples into the 'courses' table
INSERT INTO courses (created_at, deleted_at, updated_at, id, description, hours, hours_done, hours_left, name, group_id,
                     subject_id)
VALUES ('2023-08-12 20:15:00+00', NULL, '2023-08-12 20:15:00+00', '3e2d3c4b-5eef-4a95-8dcd-5f1b2a2c37d1',
        'Creative Writing for 11th Graders', 60, 0, 60, 'Creative Writing I', 'a5c8a351-6a61-4b94-99a9-7351f4e6d8b1',
        'f216b8f9-6c0f-4586-80d3-9305c06ec9a5'),
       ('2023-08-12 21:30:00+00', NULL, '2023-08-12 21:30:00+00', '3e2d3c4b-5eef-4a95-8dcd-5f1b2a2c37d2',
        'Computer Graphics for 12th Graders', 60, 0, 60, 'Graphics I', 'e7f319d2-3d0e-42e3-99e9-4b1c25c60745',
        '4a3ebc8d-53e6-4643-9d86-ea39f1d4cfcf'),
       ('2023-08-12 22:45:00+00', NULL, '2023-08-12 22:45:00+00', '3e2d3c4b-5eef-4a95-8dcd-5f1b2a2c37d3',
        'Physical Chemistry for 10th Graders', 60, 0, 60, 'Chemistry II', 'ba8f750b-1fa6-4f50-88e1-2e5b54fabb09',
        'e0b69d94-e942-418b-af44-3a2f4c01ecdb'),
       ('2023-08-12 23:00:00+00', NULL, '2023-08-12 23:00:00+00', '3e2d3c4b-5eef-4a95-8dcd-5f1b2a2c37d4',
        'Advanced Algebra for 9th Graders', 60, 0, 60, 'Algebra II', 'd6b3fc9e-39b1-4317-8c7c-84e6c6b1a9c7',
        '942ad7d6-370d-4026-befd-c2d4c7c4b5dd'),
       ('2023-08-12 23:15:00+00', NULL, '2023-08-12 23:15:00+00', '3e2d3c4b-5eef-4a95-8dcd-5f1b2a2c37d5',
        'Digital Art for 11th Graders', 60, 0, 60, 'Digital Art I', '8d592e95-8d62-43b1-a6b8-5c38b3dd45c2',
        'c849ed7f-2056-446c-b13b-88d944b8e7a4');

-- User 1 - Teacher
-- Account
INSERT INTO accounts (id, created_at, deleted_at, updated_at, avatar_url, email, email_verified, password, status)
VALUES ('5e2e759b-39c4-4fd3-b131-509015506999', '2023-08-22 13:28:29.012102 +00:00', null, null,
        'https://defaultavatar.url/', 'teacher1@example.com', false,
        '$2a$10$MRQd4iu3ct/D8wIjuzLkJ.YDE09SLvaAgzslRkFT48PcEHiZK0Pxa', 'ACTIVE');

-- User
INSERT INTO users (dtype, id, created_at, deleted_at, updated_at, address, birth_date, firstname, gender, lastname,
                   middlename, account_id)
VALUES ('Teacher', '88b9cceb-5db9-4d5d-85e2-b53befa9ab9f', '2023-08-22 13:28:29.073851 +00:00', null, null, null, null,
        'John', null, 'Doe', 'Smith', '5e2e759b-39c4-4fd3-b131-509015506999');

-- User 2 - Student
-- Account
INSERT INTO accounts (id, created_at, deleted_at, updated_at, avatar_url, email, email_verified, password, status)
VALUES ('5e2e759b-39c4-4fd3-b131-509015506915', '2023-08-22 13:28:29.012102 +00:00', null, null,
        'https://defaultavatar.url/', 'student1@example.com', false,
        '$2a$10$MRQd4iu3ct/D8wIjuzLkJ.YDE09SLvaAgzslRkFT48PcEHiZK0Pxa', 'ACTIVE');

-- User
INSERT INTO users (dtype, id, created_at, deleted_at, updated_at, address, birth_date, firstname, gender, lastname,
                   middlename, account_id)
VALUES ('Student', '88b9cceb-5db9-4d5d-85e2-b53befa9ab9d', '2023-08-22 13:28:29.073851 +00:00', null, null, null, null,
        'Alice', null, 'Smith', 'Doe', '5e2e759b-39c4-4fd3-b131-509015506915');

-- Assign Student1 to a group (Use a correct group_id)
INSERT INTO group_students (student_id, group_id)
VALUES ('88b9cceb-5db9-4d5d-85e2-b53befa9ab9d', 'a5c8a351-6a61-4b94-99a9-7351f4e6d8b1');

-- User 3 - Admin
-- Account
INSERT INTO accounts (id, created_at, deleted_at, updated_at, avatar_url, email, email_verified, password, status)
VALUES ('5e2e759b-39c4-4fd3-b131-509015506916', '2023-08-22 13:28:29.012102 +00:00', null, null,
        'https://defaultavatar.url/', 'admin1@example.com', false,
        '$2a$10$MRQd4iu3ct/D8wIjuzLkJ.YDE09SLvaAgzslRkFT48PcEHiZK0Pxa', 'ACTIVE');

-- User
INSERT INTO users (dtype, id, created_at, deleted_at, updated_at, address, birth_date, firstname, gender, lastname,
                   middlename, account_id)
VALUES ('Admin', '88b9cceb-5db9-4d5d-85e2-b53befa9ab9e', '2023-08-22 13:28:29.073851 +00:00', null, null, null, null,
        'Eve', null, 'Smith', 'Doe', '5e2e759b-39c4-4fd3-b131-509015506916');

-- User 4 - Teacher
-- Account
INSERT INTO accounts (id, created_at, deleted_at, updated_at, avatar_url, email, email_verified, password, status)
VALUES ('5e2e759b-39c4-4fd3-b131-509015506917', '2023-08-22 13:28:29.012102 +00:00', null, null,
        'https://defaultavatar.url/', 'teacher2@example.com', false,
        '$2a$10$MRQd4iu3ct/D8wIjuzLkJ.YDE09SLvaAgzslRkFT48PcEHiZK0Pxa', 'ACTIVE');

-- User
INSERT INTO users (dtype, id, created_at, deleted_at, updated_at, address, birth_date, firstname, gender, lastname,
                   middlename, account_id)
VALUES ('Teacher', '88b9cceb-5db9-4d5d-85e2-b53befa9ab9c', '2023-08-22 13:28:29.073851 +00:00', null, null, null, null,
        'Bob', null, 'Smith', 'Doe', '5e2e759b-39c4-4fd3-b131-509015506917');

COMMIT;

