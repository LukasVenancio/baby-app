package com.homebaby.repositories.video;

import com.homebaby.entities.Video;
import com.homebaby.requests.VideoFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class VideoRepository {
    private final CriteriaBuilder criteriaBuilder;
    private final EntityManager entityManager;

    public Page<Video> findByFilter(VideoFilter filter) {
        var query = this.criteriaBuilder.createQuery(Video.class);
        var root = query.from(Video.class);
        var page = filter.getPage() > 0 ? filter.getPage() - 1 : 0;
        var pageable = PageRequest.of(page, filter.getLimit());

        query.where(getPredicates(filter, root));
        query.distinct(true);

        var typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(page * filter.getLimit());
        typedQuery.setMaxResults(filter.getLimit());

        return new PageImpl<>(
                typedQuery.getResultList(),
                pageable,
                getTotal(filter)
        );
    }

    private Predicate getPredicates(VideoFilter filter, Root<Video> root) {
        Predicate predicate = criteriaBuilder.isTrue(criteriaBuilder.literal(true));

        if (Objects.nonNull(filter.getSearch())) {
            predicate = criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + filter.getSearch().toLowerCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + filter.getSearch().toLowerCase() + "%")
            );

            predicate = criteriaBuilder.or(predicate,
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("tags").get("name")), "%" + filter.getSearch() + "%")
            );
        }

        if (Objects.nonNull(filter.getYoutubeIds()))
            predicate = criteriaBuilder.and(predicate,
                    criteriaBuilder.in(root.get("youtubeId")).value(filter.getYoutubeIds())
            );

        return predicate;
    }

    private Long getTotal(VideoFilter filter) {
        var query = criteriaBuilder.createQuery(Long.class);
        var root = query.from(Video.class);

        query.select(criteriaBuilder.count(root))
                .distinct(true)
                .where(getPredicates(filter, root));

        return entityManager.createQuery(query).getSingleResult();
    }
}
