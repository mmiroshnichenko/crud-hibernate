--liquibase formatted sql

--changeset mmyroshnychenko:1 labels:label-table context:new-label-table
CREATE TABLE IF NOT EXISTS `label` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(225),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
--rollback DROP TABLE label;


--changeset mmyroshnychenko:2 labels:writer-table context:new-writer-table
CREATE TABLE IF NOT EXISTS `writer` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `firstName` varchar(225),
    `lastName` varchar(255),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
--rollback DROP TABLE writer;

--changeset mmyroshnychenko:3 labels:post-table context:new-post-table
CREATE TABLE IF NOT EXISTS `post` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `writerId` int(11),
    `content` text,
    `created` datetime NOT NULL,
    `updated` datetime NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `post_writer_fk` FOREIGN KEY (`writerId`) REFERENCES `writer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
--rollback DROP TABLE post;

--changeset mmyroshnychenko:4 labels:post-status context:add-post-status
ALTER TABLE `post` ADD `status` enum('ACTIVE', 'UNDER_REVIEW', 'DELETED') COLLATE utf8_unicode_ci NOT NULL DEFAULT 'UNDER_REVIEW';
--rollback ALTER TABLE post DROP COLUMN status;

--changeset mmyroshnychenko:5 labels:post-label-link context:post-label-link
CREATE TABLE IF NOT EXISTS `post_label` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `postId` int(11),
    `labelId` int(11),
    PRIMARY KEY (`id`),
    UNIQUE KEY(`postId`, `labelId`),
    CONSTRAINT `post_label_post_fk` FOREIGN KEY (`postId`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `post_label_label_fk` FOREIGN KEY (`labelId`) REFERENCES `label` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
--rollback DROP TABLE post_label;