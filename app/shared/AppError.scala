package shared

trait AppError extends RuntimeException {
  val message: String
  val cause: Throwable
}
