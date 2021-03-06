package slog4s.console.internal

import java.time.Instant

import slog4s.{Level, Location}

private[console] trait Formatter[F[_], T] {
  def format(
      level: Level,
      logger: String,
      msg: String,
      throwable: Option[Throwable],
      args: Map[String, T],
      now: Instant,
      threadName: String,
      location: Location
  ): F[Unit]
}
