package domain.helpers

import domain.DomainError

trait ValueObjectFactory[V, M] {

  def apply(value: V): M
  def isValid(value: V): Boolean
  def errorMessage(value: V): String

  def className: String = this.getClass.getSimpleName.dropRight(1)

  def create(value: V): Either[DomainError, M] = value match {
    case v if isValid(v) => Right(apply(v))
    case v               => Left(new DomainError(errorMessage(v)))
  }
}

trait NumericComparable[S, T] {
  def compare(x: S, y: T): Boolean
}

trait PositiveValueFactory[V, M]
    extends ValueObjectFactory[V, M]
    with NumericComparable[V, Int] {
  def isValid(value: V): Boolean = compare(value, 0)

  def errorMessage(value: V): String =
    s"$className requires non negative value: $value"
}

trait LongGInt extends NumericComparable[Long, Int] {
  override def compare(x: Long, y: Int): Boolean = x > y
}

trait NonEmptyStringFactory[M] extends ValueObjectFactory[String, M] {
  override def isValid(value: String): Boolean = !value.isEmpty

  override def errorMessage(value: String): String =
    s"$className requires non empty string"
}
