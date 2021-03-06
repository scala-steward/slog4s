package slog4s.zio

import org.scalatest.Outcome
import slog4s.shared.ContextTest
import slog4s.shared.ContextTest.Fixture
import zio.Task
import zio.interop.catz._
import zio.interop.catz.implicits._

class ZIOContextTest extends ContextTest[Task]("ZIO") {
  override protected def withFixture(test: OneArgTest): Outcome = {
    val ops =
      ZIOContextRuntime.make[Any, Throwable, Int](0).flatMap {
        zioContextRuntime =>
          import zioContextRuntime._
          Task.concurrentEffectWith { implicit F =>
            val fixture = new Fixture[Task]
            Task.effect(test(fixture))
          }
      }
    zio.Runtime.default.unsafeRun(ops)
  }
}
