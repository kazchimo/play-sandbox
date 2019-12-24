package domain.article

import domain.helpers.ValueObjectFactory._


case class Article(id: ArticleId, content: ArticleContent, tags: ArticleTags)

case class ArticleId(value: Long) extends AnyVal
object ArticleId extends PositiveValueFactory[Long, Article] with LongGInt

case class ArticleContent(value: String) extends AnyVal
object ArticleContent extends NonEmptyStringFactory[ArticleContent]

case class ArticleTag(value: String) extends AnyVal
object ArticleTag extends NonEmptyStringFactory[ArticleTag]

case class ArticleTags(value: Seq[ArticleTag])
object ArticleTags
    extends AnyValueAcceptableFactory[Seq[ArticleTag], ArticleTags]
