CREATE TABLE IF NOT EXISTS tags (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS videos (
    id UUID PRIMARY KEY,
    youtube_id VARCHAR(50) NOT NULL,
    title TEXT NOT NULL,
    published_at TIMESTAMP NOT NULL,
    description TEXT NOT NULL,
    thumbnail VARCHAR(300) NOT NULL,
    view_count INTEGER NOT NULL,
    like_count INTEGER NOT NULL,
    comment_count INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS videos_tags (
    video_id UUID NOT NULL,
    tag_id UUID NOT NULL,
    PRIMARY KEY(video_id, tag_id),
    CONSTRAINT fk_videos_tags_video FOREIGN KEY (video_id) REFERENCES videos(id),
    CONSTRAINT fk_videos_tags_tag FOREIGN KEY (tag_id) REFERENCES tags(id)
);



