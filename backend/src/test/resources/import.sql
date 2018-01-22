INSERT INTO categories (idCategory, name) VALUES (1, 'Pet Care'), (2, 'Cooking'), (3, 'Housekeeping'), (4, 'Tutoring'), (5, 'Makeup'), (6, 'Free Time'), (7, 'People Care'), (8, 'Amusement');

INSERT INTO locations (idLocation, description, latitude, longitude) VALUES (1, 'Seattle, Waszyngton, Stany Zjednoczone', 47.6062095, -122.3320708), (2, 'San Francisco, Kalifornia, Stany Zjednoczone', 37.7749295, -122.41941550000001), (3, 'White Plains, Nowy Jork, Stany Zjednoczone', 41.03398620000001, -73.76290970000002);

# username: billgates
# password: 2aNJn29r
#
# username: stevejobs
# password: F7zKwGg2
#
# username:: markzuckerberg
# password: Zqq37aDT

INSERT INTO users (username, password, email, firstName, lastName, role, idLocation) VALUES ('billgates', '$2a$10$tjZR0.Hj4DExo1X81K0rf.RpD7e.bLSuMwoej5z8jAB6iB751TbcS','billgates@test.com', 'Bill', 'Gates', 'USER', 1), ('stevejobs', '$2a$10$s4Fsgj.U6Cql.3i7BD90VuDNWHwRW6G.gf3N781B47FrEcmXGk2NO','stevejobs@test.com', 'Steve', 'Jobs', 'USER', 2), ('markzuckerberg', '$2a$10$tgIejFFdFbHBL4yj/XMBu.Cy1yF0b5d0bcffY22SvOia8h60gEUWe','markzuckerberg@test.com', 'Mark', 'Zuckerberg', 'USER', 3);

INSERT INTO users (username, password, email, firstName, lastName, role, idLocation) VALUES ('usernoaccount', '$2a$10$tjZR0.Hj4DExo1X81K0rf.RpD7e.bLSuMwoej5z8jAB6iB751TbcS','account@test.com', 'Account', 'None', 'USER', 2);

DELETE FROM accounts WHERE owner = 'usernoaccount';

INSERT INTO locations (idLocation, description, latitude, longitude) VALUES (2, 'San Francisco, Kalifornia, Stany Zjednoczone', 37.7749295, -122.41941550000001);

INSERT INTO adverts (idAdvert, active, type, owner, contractor, title, description, idCategory, value, idLocation) VALUES (1, true, 'SEEK', 'billgates', null, 'Some title', 'Some description', 2, 4, 0);

INSERT INTO adverts (idAdvert, active, type, owner, contractor, title, description, idCategory, value, idLocation) VALUES (2, true, 'SEEK', 'stevejobs', null, 'Cake baking', 'I look for someone who could help me bake a birthday cake for my wife', 2, 4, 2);

INSERT INTO adverts (idAdvert, active, type, owner, contractor, title, description, idCategory, value, idLocation) VALUES (3, true, 'SEEK', 'billgates', null, 'Java', 'I look for someone who could help me learn Java', 2, 4, 1);

INSERT INTO adverts (idAdvert, active, type, owner, contractor, title, description, idCategory, value, idLocation) VALUES (4, true, 'SEEK', null, null, 'Some title', 'Some description', 2, 4, 0);

INSERT INTO users (username, password, email, firstName, lastName, role, idLocation) VALUES ('usernolocation', '$2a$10$tjZR0.Hj4DExo1X81K0rf.RpD7e.bLSuMwoej5z8jAB6iB751TbcS','location@test.com', 'Location', 'None', 'USER', 0);
